import {Category} from "./category";

export interface Product {
  id: number;
  name?: string;
  image?: File | null;
  description?: string;
  price?: number;
  imageUrl?: string;
  quantityStock?: number;
  category?: Category;
  categoryId?: number;
}
