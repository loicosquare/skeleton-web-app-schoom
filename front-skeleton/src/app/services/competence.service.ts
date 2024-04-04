import { Injectable } from '@angular/core';
import {Competence} from "../models/competence.model";
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http"
import {catchError, Observable, tap, throwError} from "rxjs";
import { CompetenceRequest } from "../models/utils/competenceRequest.model"

@Injectable({
  providedIn: 'root'
})
export class CompetenceService {

  private apiUrl = 'http://localhost:8081/competences';

  constructor(private http: HttpClient) { }

  saveCompetence(competence: CompetenceRequest, userId: number): Observable<Competence> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<Competence>(`${this.apiUrl}/add?userId=${userId}`, competence, { headers })
      .pipe(
        tap((savedCompetence: Competence) => {
          console.log('Compétence enregistrée avec succès:', savedCompetence);
        }),
        catchError(this.handleError)
      );
  }

  updateCompetence(competence: Competence): Observable<Competence> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.put<Competence>(`${this.apiUrl}/${competence.id}`, competence, { headers })
      .pipe(
        tap((updatedCompetence: Competence) => {
          console.log('Compétence mise à jour avec succès:', updatedCompetence);
        }),
        catchError(this.handleError)
      );
  }

  getAllCompetencesByUserId(userId: number): Observable<Competence[]> {
    return this.http.get<Competence[]>(`${this.apiUrl}/user/${userId}`)
      .pipe(
        tap((competences: Competence[]) => {
          console.log('Compétences récupérées avec succès:', competences);
        }),
        catchError(this.handleError)
      );
  }

  deleteCompetence(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`)
      .pipe(
        tap(() => {
          console.log('Compétence supprimée avec succès');
        }),
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`An error occurred - Error code: ${error.status}`);
  }
}
