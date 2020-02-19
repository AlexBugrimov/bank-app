import React, {useEffect, useReducer} from 'react';
import './Banks.css';
import Spinner from "react-bootstrap/Spinner";
import Bank from './Bank';

const initialState = {
    loading: true,
    error: null,
    banks: []
};

const reducer = (state = initialState, action) => {
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
};

async function fetchBanks() {
    return await (await fetch('/api/v1/banks', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })).json();
}

const Banks = () => {
    const [{banks, loading}, dispatch] = useReducer(reducer, initialState);
    useEffect(() => {
        fetchBanks().then(banks => dispatch({type: 'FETCH_SUCCESS_BANKS', payload: banks}));
    }, [loading]);
    return <>
        <h3>Banks</h3>
        <div className="Cards">
            {loading ? <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner> : banks.map(({bankId, name}) => <Bank key={bankId} bankId={bankId} name={name}/>)}
        </div>
    </>
};
export default Banks;