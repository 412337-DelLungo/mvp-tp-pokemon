-- Pokemon TCG Database Schema
-- PostgreSQL initialization migration

-- Users table
CREATE TABLE users (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    username character varying(50) NOT NULL,
    email character varying(120),
    password_hash text,
    display_name character varying(80),
    role character varying(30) DEFAULT 'PLAYER' NOT NULL,
    status character varying(30) DEFAULT 'ACTIVE' NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_username_key UNIQUE (username),
    CONSTRAINT users_email_key UNIQUE (email)
);

-- Guest players table
CREATE TABLE guest_players (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    display_name character varying(80) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT guest_players_pkey PRIMARY KEY (id)
);

-- Cards table
CREATE TABLE cards (
    id character varying(80) NOT NULL,
    name character varying(160) NOT NULL,
    supertype character varying(30) NOT NULL,
    subtypes text[] DEFAULT '{}'::text[] NOT NULL,
    set_code character varying(30) NOT NULL,
    number character varying(30),
    rarity character varying(80),
    image_small_url text,
    image_large_url text,
    hp integer,
    pokemon_stage character varying(30),
    evolves_from character varying(160),
    pokemon_types text[] DEFAULT '{}'::text[] NOT NULL,
    retreat_cost text[] DEFAULT '{}'::text[] NOT NULL,
    converted_retreat_cost integer,
    is_ex boolean DEFAULT false NOT NULL,
    is_mega boolean DEFAULT false NOT NULL,
    energy_card_type character varying(30),
    provides_energy_types text[] DEFAULT '{}'::text[] NOT NULL,
    trainer_subtype character varying(30),
    is_ace_spec boolean DEFAULT false NOT NULL,
    rules_text text[] DEFAULT '{}'::text[] NOT NULL,
    raw_json jsonb NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT cards_pkey PRIMARY KEY (id)
);

-- Card attacks table
CREATE TABLE card_attacks (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    card_id character varying(80) NOT NULL,
    attack_index integer NOT NULL,
    name character varying(160) NOT NULL,
    printed_cost text[] DEFAULT '{}'::text[] NOT NULL,
    converted_energy_cost integer DEFAULT 0 NOT NULL,
    damage_text character varying(40),
    base_damage integer,
    effect_text text,
    effect_code character varying(80),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT card_attacks_pkey PRIMARY KEY (id),
    CONSTRAINT card_attacks_card_id_attack_index_key UNIQUE (card_id, attack_index)
);

-- Card weaknesses table
CREATE TABLE card_weaknesses (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    card_id character varying(80) NOT NULL,
    energy_type character varying(30) NOT NULL,
    multiplier integer DEFAULT 2 NOT NULL,
    CONSTRAINT card_weaknesses_pkey PRIMARY KEY (id)
);

-- Card resistances table
CREATE TABLE card_resistances (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    card_id character varying(80) NOT NULL,
    energy_type character varying(30) NOT NULL,
    value integer DEFAULT '-20'::integer NOT NULL,
    CONSTRAINT card_resistances_pkey PRIMARY KEY (id)
);

-- Decks table
CREATE TABLE decks (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    owner_user_id uuid,
    name character varying(120) NOT NULL,
    source character varying(30) DEFAULT 'USER' NOT NULL,
    valid boolean DEFAULT false NOT NULL,
    validation_errors jsonb DEFAULT '[]'::jsonb NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT decks_pkey PRIMARY KEY (id)
);

-- Deck cards table
CREATE TABLE deck_cards (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    deck_id uuid NOT NULL,
    card_id character varying(80) NOT NULL,
    quantity integer NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT deck_cards_pkey PRIMARY KEY (id),
    CONSTRAINT deck_cards_quantity_check CHECK (quantity > 0),
    CONSTRAINT deck_cards_deck_id_card_id_key UNIQUE (deck_id, card_id)
);

-- Matches table
CREATE TABLE matches (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    status character varying(30) NOT NULL,
    current_phase character varying(30),
    turn_number integer DEFAULT 0 NOT NULL,
    current_player_id uuid,
    first_player_id uuid,
    winner_player_id uuid,
    finish_reason character varying(60),
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    finished_at timestamp without time zone,
    latest_state_version bigint,
    CONSTRAINT matches_pkey PRIMARY KEY (id)
);

-- Match players table
CREATE TABLE match_players (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    match_id uuid NOT NULL,
    player_id uuid NOT NULL,
    player_kind character varying(20) NOT NULL,
    side character varying(30) NOT NULL,
    deck_id uuid,
    display_name character varying(80) NOT NULL,
    joined_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT match_players_pkey PRIMARY KEY (id),
    CONSTRAINT match_players_match_id_player_id_key UNIQUE (match_id, player_id),
    CONSTRAINT match_players_match_id_side_key UNIQUE (match_id, side)
);

-- Match states table
CREATE TABLE match_states (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    match_id uuid NOT NULL,
    version bigint NOT NULL,
    serialized_state jsonb NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT match_states_pkey PRIMARY KEY (id),
    CONSTRAINT match_states_match_id_version_key UNIQUE (match_id, version)
);

-- Match logs table
CREATE TABLE match_logs (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    match_id uuid NOT NULL,
    version bigint NOT NULL,
    turn_number integer NOT NULL,
    player_id uuid,
    action_type character varying(80),
    event_type character varying(80) NOT NULL,
    result character varying(30) NOT NULL,
    message text,
    payload jsonb DEFAULT '{}'::jsonb NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT match_logs_pkey PRIMARY KEY (id)
);

-- Indexes
CREATE INDEX idx_cards_name ON cards (name);
CREATE INDEX idx_cards_set_code ON cards (set_code);
CREATE INDEX idx_cards_supertype ON cards (supertype);
CREATE INDEX idx_cards_trainer_subtype ON cards (trainer_subtype);
CREATE INDEX idx_cards_is_ace_spec ON cards (is_ace_spec);
CREATE INDEX idx_decks_owner_user_id ON decks (owner_user_id);
CREATE INDEX idx_decks_source ON decks (source);
CREATE INDEX idx_matches_status ON matches (status);
CREATE INDEX idx_matches_created_at ON matches (created_at);
CREATE INDEX idx_match_players_match_id ON match_players (match_id);
CREATE INDEX idx_match_players_player_id ON match_players (player_id);
CREATE INDEX idx_match_states_match_version ON match_states (match_id, version DESC);
CREATE INDEX idx_match_states_json ON match_states USING gin (serialized_state);
CREATE INDEX idx_match_logs_match_turn ON match_logs (match_id, turn_number);
CREATE INDEX idx_match_logs_match_version ON match_logs (match_id, version);
CREATE INDEX idx_match_logs_event_type ON match_logs (event_type);
CREATE INDEX idx_match_logs_payload ON match_logs USING gin (payload);

-- Foreign Keys
ALTER TABLE card_attacks ADD CONSTRAINT card_attacks_card_id_fkey
    FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE;
ALTER TABLE card_weaknesses ADD CONSTRAINT card_weaknesses_card_id_fkey
    FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE;
ALTER TABLE card_resistances ADD CONSTRAINT card_resistances_card_id_fkey
    FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE;
ALTER TABLE decks ADD CONSTRAINT decks_owner_user_id_fkey
    FOREIGN KEY (owner_user_id) REFERENCES users(id) ON DELETE SET NULL;
ALTER TABLE deck_cards ADD CONSTRAINT deck_cards_deck_id_fkey
    FOREIGN KEY (deck_id) REFERENCES decks(id) ON DELETE CASCADE;
ALTER TABLE deck_cards ADD CONSTRAINT deck_cards_card_id_fkey
    FOREIGN KEY (card_id) REFERENCES cards(id);
ALTER TABLE match_players ADD CONSTRAINT match_players_match_id_fkey
    FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE;
ALTER TABLE match_players ADD CONSTRAINT match_players_deck_id_fkey
    FOREIGN KEY (deck_id) REFERENCES decks(id);
ALTER TABLE match_states ADD CONSTRAINT match_states_match_id_fkey
    FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE;
ALTER TABLE match_logs ADD CONSTRAINT match_logs_match_id_fkey
    FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE;