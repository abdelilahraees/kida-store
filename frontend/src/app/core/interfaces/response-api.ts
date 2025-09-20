import {ErrorMassage} from "./message-error";
import {Status} from "./status";

export interface ResponseApi<T> {
  data?: T;
  errors?: ErrorMassage[];
  status?: Status;
  [property: string]: any;
}
