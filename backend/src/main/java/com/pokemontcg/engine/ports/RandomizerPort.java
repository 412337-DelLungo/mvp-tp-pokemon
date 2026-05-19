package com.pokemontcg.engine.ports;

import java.util.List;

public interface RandomizerPort {
    <T> T shuffleAndPick(List<T> items, int count);
    int nextInt(int bound);
}