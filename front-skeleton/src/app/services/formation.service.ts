import { Injectable } from '@angular/core';

import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http"
import {Observable, throwError} from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import {Formation} from "../models/formation.model";
import { FormationRequest } from "../models/utils/formationRequest.model"

@Injectable({
  providedIn: 'root'
})
export class FormationService {
  private baseUrl = 'http://localhost:8081/formations';

  constructor(private http: HttpClient) { }

  saveFormation(userId: number, formationRequest: FormationRequest): Observable<Formation> {
    return this.http.post<Formation>(`${this.baseUrl}/add?userId=${userId}`, formationRequest)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  updateFormation(formation: Formation): Observable<Formation> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.put<Formation>(`${this.baseUrl}/${formation.id}`, formation, { headers })
      .pipe(
        tap((updatedFormation: Formation) => {
          console.log('Formation mise à jour avec succès:', updatedFormation);
        }),
        catchError(this.handleError)
      );
  }

  getAllFormationsByUserId(userId: number): Observable<Formation[]> {
    return this.http.get<Formation[]>(`${this.baseUrl}/user/${userId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  deleteFormation(id: number): Observable<void> {
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
