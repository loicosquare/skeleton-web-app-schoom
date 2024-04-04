import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import {Loisir} from "../models/loisir.model";


@Injectable({
  providedIn: 'root'
})
export class LoisirService {
  private baseUrl = 'http://localhost:8081/loisirs';

  constructor(private http: HttpClient) { }

  saveLoisir(userId: number, hobby: string): Observable<Loisir> {
    const params = { userId, hobby };
    return this.http.post<Loisir>(`${this.baseUrl}/add`, params)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  updateLoisir(loisir: Loisir): Observable<Loisir> {
    return this.http.put<Loisir>(`${this.baseUrl}/${loisir.id}`, loisir)
      .pipe(
        tap((updatedLoisir: Loisir) => {
          console.log('Loisir mis à jour avec succès:', updatedLoisir);
        }),
        catchError(this.handleError)
      );
  }

  getAllLoisirsByUserId(userId: number): Observable<Loisir[]> {
    return this.http.get<Loisir[]>(`${this.baseUrl}/user/${userId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  deleteLoisir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`An error occurred - Error code: ${error.status}`);
  }
}
