import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http"
import {catchError, Observable, tap, throwError} from "rxjs";
import {Experience} from "../models/experience.model";

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {
  private baseUrl = 'http://localhost:8081/experiences';

  constructor(private http: HttpClient) { }

  saveExperience(idUtilisateur: number, titre: string, entreprise: string, lieu: string, description: string, dateDebut: string, dateFin: string): Observable<any> {
    const params = { idUtilisateur, titre, entreprise, lieu, description, dateDebut, dateFin };
    return this.http.post(`${this.baseUrl}/add`, params)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  updateExperience(experience: Experience): Observable<Experience> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.put<Experience>(`${this.baseUrl}/${experience.id}`, experience, { headers })
      .pipe(
        tap((updatedExperience: Experience) => {
          console.log('Expérience mise à jour avec succès:', updatedExperience);
        }),
        catchError(this.handleError)
      );
  }

  getAllExperiencesByUserId(userId: number): Observable<Experience[]> {
    return this.http.get<Experience[]>(`${this.baseUrl}/user/${userId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  deleteExperience(id: number): Observable<void> {
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
