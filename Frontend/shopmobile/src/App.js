import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./components/login/Login";
import Register from "./components/login/Register";
import FindId from "./components/login/FindId";
import ResultId from "./components/login/ResultId";
import Main from "./components/main/Main";
import Qrscan from "./components/main/QRReader";
import Qrmake from "./components/main/QRMaker";
import Nav from "./components/main/Nav";
import Userinfo from "./components/userinfo/UserInfo"
import Card from "./components/userinfo/Card";
import Payment from "./components/userinfo/Payment";
import PaymentDetail from "./components/userinfo/PaymentDetail";
import Account from "./components/userinfo/Account";

function App() {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/app" element={<Main />} />
        <Route path="/app/login" element={<Login />} />
        <Route path="/app/register" element={<Register />} />
        <Route path="/app/findid" element={<FindId />} />
        <Route path="/app/resultid" element={<ResultId />} />
        <Route path="/app/qrreader" element={<Qrscan />} />
        <Route path="/app/qrmaker" element={<Qrmake />} />
        <Route path="/app/userinfo" element={<Userinfo />} />
        <Route path="/app/card" element={<Card />} />
        <Route path="/app/payment" element={<Payment />} />
        <Route path="/app/PaymentDetail" element={<PaymentDetail />} />
        <Route path="/app/account" element={<Account />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
