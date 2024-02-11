import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class BookingService {
    private REST_API_SERVER = 'http://localhost:8080';
    private httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        })
    };

    constructor(private http: HttpClient) {}

    getAll(): Observable<any[]> {
        const url = `${this.REST_API_SERVER}/booking`;
        return this.http.get<any[]>(url);
    }

    getAllByCurrentUser(id: number): Observable<any[]> {
        const url = `${this.REST_API_SERVER}/booking/user/${id}`;
        return this.http.get<any[]>(url);
    }

    save(data): Observable<any> {
        const url = `${this.REST_API_SERVER}/booking`;
        return this.http.post<any>(url, data);
    }

    updateStatus(data): Observable<any> {
        const url = `${this.REST_API_SERVER}/booking/status`;
        return this.http.put<any>(url, data);
    }

    delete(id: number) {
        const url = `${this.REST_API_SERVER}/booking/${id}`;
        return this.http.delete<any>(url);
    }

    getAppointment(year: string): Observable<any[]> {
        const url = `${this.REST_API_SERVER}/admin/revenue/service?year=${year}`;
        return this.http.get<any[]>(url, this.httpOptions)
            .pipe(catchError(this.handleError));
    }

    // Handles error when send data to server
    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            console.log('An error :', error.error.message);
        } else {
            console.log(`Backend return code ${error.status},` + `body was: ${error.error} `);
        }
        return throwError(error);
    }
}
