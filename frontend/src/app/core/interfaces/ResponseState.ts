import {PaginationData} from "./paginationData";
import {Product} from "./product";

export interface ResponseState<T> {
  data?: T;
  error?: string;
  loading: boolean;
}
