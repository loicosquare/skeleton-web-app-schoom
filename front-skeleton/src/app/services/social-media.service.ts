import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import {SocialMedia} from "../models/social-media.model";
import { SocialMediaRequest } from "../models/utils/social-mediaRequest.model"

@Injectable({
  providedIn: 'root'
})
export class SocialMediaService {
  private baseUrl = 'http://localhost:8081/social-media';

  constructor(private http: HttpClient) { }

  saveSocialMedia(userId: number, socialMediaRequest: SocialMediaRequest): Observable<SocialMedia> {
    return this.http.post<SocialMedia>(`${this.baseUrl}/add?userId=${userId}`, socialMediaRequest)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  updateSocialMedia(socialMedia: SocialMedia): Observable<SocialMedia> {
    return this.http.put<SocialMedia>(`${this.baseUrl}/${socialMedia.id}`, socialMedia)
      .pipe(
        tap((updatedSocialMedia: SocialMedia) => {
          console.log('Social media mis à jour avec succès:', updatedSocialMedia);
        }),
        catchError(this.handleError)
      );
  }

  getAllSocialMediaByUserId(userId: number): Observable<SocialMedia[]> {
    return this.http.get<SocialMedia[]>(`${this.baseUrl}/user/${userId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  deleteSocialMedia(id: number): Observable<void> {
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
