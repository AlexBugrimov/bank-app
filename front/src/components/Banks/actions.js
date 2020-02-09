import { postData } from "../../api/apiFetch";

export const createNewBank = (bank = {}) => postData('/api/v1/banks', bank);
