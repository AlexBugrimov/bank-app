import React, {useEffect, useState} from 'react';
import Button from "react-bootstrap/Button";
import {createNewBank, getAllBanks} from "./actions";
import Form from "react-bootstrap/Form";
import './Banks.css';
import Spinner from "react-bootstrap/Spinner";
import Bank from './Bank';
import {connect} from 'react-redux';
import {setBanks} from "../../actions/banks";

const Banks = () => {
    const [banks, setBanks] = useState([]);
    const [bank, setBank] = useState({name: ''});
    const [isLoad, setIsLoad] = useState(true);

    const createBank = async (e) => {
        e.preventDefault();
        bank.name && setBanks([
            ...banks,
            await createNewBank(bank)
        ]);
        setBank({name: ''});
    };

    const getBanks = async () => {
        const allBanks = await getAllBanks();
        setBanks([...allBanks]);
        setIsLoad(false);
    };

    const handleChange = ({target: {value}}) => {
        setBank({
            name: value
        });
    };

    useEffect(() => {
        getBanks().then(r => console.log('Banks is loaded...'));
    }, [isLoad]);

    const getSpinner = () => <Spinner animation="border" role="status">
        <span className="sr-only">Loading...</span>
    </Spinner>;

    return (<>
        <div><h3>Banks</h3>
            <Form>
                <Form.Group controlId="formBanks">
                    <Form.Label>Банк</Form.Label>
                    <Form.Control
                        value={bank.name}
                        type="text"
                        placeholder="Введите название банка"
                        onChange={handleChange}/>
                </Form.Group>
                <Button
                    variant='success'
                    onClick={createBank}
                    type='submit'
                >Создать банк</Button>
            </Form>
            {isLoad ? getSpinner() : <div className="Cards">
                {banks.map(({bankId, name}) => <Bank key={bankId} bankId={bankId} name={name}/>)}
            </div>}
        </div>
        </>
    )
};

const mapStateToProps = state => ({
   ...state
});

const mapDispatchToProps = dispatch => ({
    setBanks: banks => dispatch(setBanks(banks))
});

export default connect(mapStateToProps, mapDispatchToProps)(Banks);