import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import React from "react";

export default () => <Navbar bg="light" variant="light">
    <Navbar.Brand href="#home">Bank App</Navbar.Brand>
    <Nav className="mr-auto">
        <Nav.Link href="#features">Банки</Nav.Link>
        <Nav.Link href="#pricing">Клиенты</Nav.Link>
        <Nav.Link href="#pricing">Счета</Nav.Link>
    </Nav>
</Navbar>