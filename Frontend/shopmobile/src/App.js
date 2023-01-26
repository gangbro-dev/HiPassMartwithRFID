import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/login/Login";
import Register from "./components/login/Register";
import FindId from "./components/login/FindId";
import ResultId from "./components/login/ResultId";
import Main from "./components/main/Main";
import Qrscan from "./components/main/QRReader";
import Nav from "./components/main/Nav";
import Userinfo from "./components/userinfo/UserInfo"
import Card from "./components/userinfo/Card";
import Payment from "./components/userinfo/Payment";
import Account from "./components/userinfo/Account";

function App() {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="findid" element={<FindId />} />
        <Route path="resultid" element={<ResultId />} />
        <Route path="main" element={<Main />} />
        <Route path="qrreader" element={<Qrscan />} />
        <Route path="userinfo" element={<Userinfo />} />
        <Route path="card" element={<Card />} />
        <Route path="payment" element={<Payment />} />
        <Route path="account" element={<Account />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
