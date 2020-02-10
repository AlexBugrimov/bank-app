import React, {useEffect, useState} from 'react';
import Button from "react-bootstrap/Button";
import {createNewBank, getAllBanks} from "./actions";
import Form from "react-bootstrap/Form";
import Card from "react-bootstrap/Card";
import './Banks.css';
import Spinner from "react-bootstrap/Spinner";

export default () => {
    const [banks, setBanks] = useState([]);
    const [bank, setBank] = useState({name: ''});
    const [isLoad, setIsLoad] = useState(true);

    const createBank = async (e) => {
        e.preventDefault();
        bank.name && setBanks([
            ...banks,
            await createNewBank(bank)
        ])
    };

    const handleChange = (e) => {
        setBank({
            name: e.target.value
        })
    };

    const getBanks = async () => {
        const allBanks = await getAllBanks();
        setBanks( [...allBanks]);
        setIsLoad(false);
    };

    useEffect(() => {
        getBanks().then(r => console.log('Banks is loaded...'));
    }, [isLoad]);

    return (
        <div><h3>Banks</h3>
            <Form>
                <Form.Group controlId="formBanks">
                    <Form.Label>Банк</Form.Label>
                    <Form.Control
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
            {isLoad ? <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner> : <div className="Cards">
                {banks && banks.map(({bankId, name}) => <Card key={bankId} className="Card" style={{width: '18rem'}}
                                                              border={"success"}>
                    <Card.Body>
                        <Card.Title className="Card__title">{name}</Card.Title>
                        <Card.Subtitle className="mb-2 text-muted">Идентефикатор: {bankId}</Card.Subtitle>
                    </Card.Body>
                </Card>)}
            </div>}
        </div>
    )
}