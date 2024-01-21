import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehicule } from '../types/vehicule.type';

@Injectable({
  providedIn: 'root',
})
export class VehiculeService {
  private apiUrl = '/api/json/vehicules';

  constructor(private http: HttpClient) {}

  getVehicules(): Observable<Vehicule[]> {
    return this.http.get<Vehicule[]>(this.apiUrl);
  }

  addVehicule(newVehicule: Vehicule): Observable<any> {
    return this.http.post(this.apiUrl, newVehicule);
  }

  editVehicule(id: string, vehicule: Vehicule): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, vehicule);
  }

  deleteVehicule(id: string): Observable<any> {
    console.log(`${this.apiUrl}/${id}`);
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
