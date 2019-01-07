import {Crew} from '../../crew/models/model-crew/crew';
import {Deserializable} from '../../crew/models/model-crew/deserializable';

export class Movie implements Deserializable {
  id;
  title: string;
  duration;
  productionCountry: string;
  productionYear: string;
  directors: Crew;
  cast: string;
  movieShow: string;
  types: string[] = [];

  deserialize(input: any): Movie {
    Object.assign(this, input);
    this.directors = new Crew().deserialize(input.directors);
    return this;
  }
}
