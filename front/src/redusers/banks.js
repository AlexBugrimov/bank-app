const initialState = {
    loading: true,
    error: null,
    banks: []
};

export default (state = initialState, action) => {
    console.log('STATE', state);
    // console.log('type', type);
    // console.log('payload', payload);
    switch (action.type) {
        case 'FETCH_SUCCESS_BANKS':
            return {
                ...state,
                banks: action.payload,
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
                    action.payload
                ]
            };
        default:
            return state;
    }
}