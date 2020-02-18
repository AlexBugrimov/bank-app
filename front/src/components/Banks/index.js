import React, {useEffect, useReducer} from 'react';
import './Banks.css';
import Spinner from "react-bootstrap/Spinner";
import reducer from './../../redusers/banks'
import initialState from './../../redusers/banks'

const Banks = () => {
    const [state, dispatch] = useReducer(reducer, initialState);
    useEffect(async () => {
        const response = await fetch('/api/v1/banks', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        const banks = await response.json();
        console.log(banks)
        return dispatch({type: 'FETCH_SUCCESS_BANKS', payload: banks});


    }, []);
// dispatch({ type: 'FETCH_SUCCESS_BANKS', payload: response.json()});)
//     const spinner = () => <Spinner animation="border" role="status">
//         <span className="sr-only">Loading...</span>
//     </Spinner>;

    return (<>
            <div><h3>Banks</h3>
                {/*{<div className="Cards">*/}
                {/*    {state.banks ? spinner : state.banks.map(({bankId, name}) => <Bank key={bankId} bankId={bankId} name={name}/>)}*/}
                {/*</div>}*/}
            </div>
        </>
    )
};
export default Banks;