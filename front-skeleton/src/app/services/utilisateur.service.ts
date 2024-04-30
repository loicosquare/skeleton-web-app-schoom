import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from "rxjs"
import { Competence } from "../models/competence.model"
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http"
import { UtilisateurRequest } from "../models/utils/utilisateurRequest.model"
import { Utilisateur } from "../models/utilisateur.model"

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  private apiUrl = 'http://localhost:8081/utilisateurs';

  constructor(private http: HttpClient) { }

  getUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.apiUrl+"/all")
      .pipe(
        tap((utilisateurs: Utilisateur[]) => {
          console.log('Utilisateurs récupérés avec succès:', utilisateurs);
        }),
        catchError(this.handleError)
      );
  }

  updateUtilisateur(utilisateurRequest: UtilisateurRequest): Observable<Utilisateur> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<Utilisateur>(`${this.apiUrl}/add`, utilisateurRequest, { headers })
      .pipe(
        tap((updatedUser: Utilisateur) => {
          console.log('Utilisateur MAJ avec succès:', updatedUser);
        }),
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`An error occurred - Error code: ${error.status}`);
  }
}
