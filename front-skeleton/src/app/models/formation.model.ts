import {Utilisateur} from "./utilisateur.model";

export interface Formation {
  id: number;
  utilisateur: any;
  diplome: string;
  domaineEtudes: string;
  ecole: string;
  lieu: string;
  anneeDebut: number;
  anneeFin: number;
}
