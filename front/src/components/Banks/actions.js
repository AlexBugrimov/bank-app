import {getAllData, postData} from "../../api/apiFetch";

const API_V1_BANKS = '/api/v1/banks';

export const createNewBank = (bank = {}) => postData(API_V1_BANKS, bank);

export const getAllBanks = () => getAllData(API_V1_BANKS);
