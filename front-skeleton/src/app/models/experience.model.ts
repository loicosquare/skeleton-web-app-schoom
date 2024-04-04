import {Utilisateur} from "./utilisateur.model";

export interface Experience {
  id: number;
  utilisateur: any;
  titre: string;
  entreprise: string;
  lieu: string;
  description: string;
  dateDebut: Date;
  dateFin: Date;
}
