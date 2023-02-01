import React from 'react';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";
import Dashboard from './components/Dashboard';
import Nav from './components/Nav';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/admin" element={<Dashboard />} />
        <Route path="/admin/dashboard" element={<Dashboard />} />
        <Route path="/admin/nav" element={<Nav />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
