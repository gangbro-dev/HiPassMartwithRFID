import React from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import Login from "./components/login/Login";
import Register from "./components/login/Register";
import FindId from "./components/login/FindId";
import ResultId from "./components/login/ResultId";

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="findid" element={<FindId />} />
        <Route path="resultid" element={<ResultId />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
