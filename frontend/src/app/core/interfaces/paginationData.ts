export interface PaginationData<T> {
  content: T[];
  pageNumber?: number;
  totalPages?: number;
  totalElements?: number;
  hasPreviousPage?: boolean;
  hasNextPage?: boolean;
  previousPageUrl?: string;
  nextPageUrl?: string;
}
