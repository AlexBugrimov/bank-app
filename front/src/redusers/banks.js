const initialState = {
    loading: true,
    error: null,
    banks: []
};

export default (state = initialState, {type, payload}) => {
    switch (type) {
        case 'FETCH_SUCCESS_BANKS':
            return {
                ...state,
                banks: payload,
                loading: false,
                error: null
            };
        case 'FETCH_ERROR_BANKS':
            return {
                ...state,
                banks: [],
                loading: false,
                error: 'Fetch error banks'
            };
        case 'SUCCESS_ADD_BANK':
            return {
                ...state,
                banks: [
                    ...state.banks,
                    payload
                ]
            };
        default:
            return state;
    }
}