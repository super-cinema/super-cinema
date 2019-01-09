import {Deserializable} from './deserializable';

export class Crew
  // implements Deserializable
{
  id?: string;
  name: string;
  surname: string;
  crewRoles: string[] = [];

  // deserialize(input: any): this {
  //   Object.assign(this, input);
  //   return this;
  // }
}
