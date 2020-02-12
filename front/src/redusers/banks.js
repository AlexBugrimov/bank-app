const initialState = {
    banks: [],
};

export default (state = initialState, action) => {
    switch (action.type) {
        case 'SET_BANKS':
            return {
                ...state,
                banks: action.payload
            };
        case 'ADD_BANK':
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