import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ApiClientService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  get<T>(path: string, options?: { headers?: HttpHeaders }): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}${path}`, options);
  }

  post<T>(path: string, body: unknown, options?: { headers?: HttpHeaders }): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}${path}`, body, options);
  }

  put<T>(path: string, body: unknown, options?: { headers?: HttpHeaders }): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}${path}`, body, options);
  }

  delete<T>(path: string, options?: { headers?: HttpHeaders }): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}${path}`, options);
  }
}