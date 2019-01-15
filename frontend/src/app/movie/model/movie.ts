import {Crew} from '../../crew/model/crew';

export class Movie {
  id;
  title: string;
  duration;
  productionCountry: string;
  productionYear: string;
  directors: Crew[];
  cast?: Crew[];
  movieShow?: string;
  types: string[] = [];
}
