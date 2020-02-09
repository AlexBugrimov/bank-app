import React from 'react';
import Button from "react-bootstrap/Button";
import {createNewBank} from "./actions";

export default ( {banks} ) => {

    const createBank = async () => {
        const  bank = await createNewBank({name: 'VTB-Bank'});
        console.log('BANK', bank);
    };

    return (
        <div><h3>Banks</h3>
            <Button
                variant='success'
                onClick={createBank}
            >Создать банк</Button>
        </div>
    )
}