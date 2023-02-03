import * as React from "react";
import { useState } from "react";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import HomeIcon from "@mui/icons-material/Home";
import QrCodeIcon from "@mui/icons-material/QrCode";
import QrCodeScannerIcon from "@mui/icons-material/QrCodeScanner";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { useNavigate } from "react-router-dom";
import { CssBaseline, Paper } from "@mui/material";

const Nav = () => {
  const [value, setValue] = useState(0);
  const loginCheck = true;

  const navigate = useNavigate();


  if (!loginCheck) {
    return (
      <Paper
        sx={{ position: "fixed", bottom: 0, left: 0, right: 0, zIndex:5 }}
        elevation={3}
      >
        <CssBaseline />
          <BottomNavigation
            showLabels
            value={value}
            onChange={(event, newValue) => {
              setValue(newValue);
            }}
          >
            <BottomNavigationAction
              label="메인화면"
              value={value}
              onClick={() => navigate("/app")}
              icon={<HomeIcon fontSize="large" />}
            />
            <BottomNavigationAction
              label="QR생성"
              value={value}
              onClick={() => navigate("/app/login")}
              icon={<QrCodeIcon fontSize="large" />}
            />
            <BottomNavigationAction
              label="QR스캔"
              value={value}
              onClick={() => navigate("/app/login")}
              icon={<QrCodeScannerIcon fontSize="large" />}
            />
            <BottomNavigationAction
              label="로그인"
              value={value}
              onClick={() => navigate("/app/login")}
              icon={<AccountCircleIcon fontSize="large" />}
            />
          </BottomNavigation>
      </Paper>
    );
  } else if (loginCheck) {
    return (
      <Paper
        sx={{ position: "fixed", bottom: 0, left: 0, right: 0, zIndex:5 }}
        elevation={3}
      >
        <CssBaseline />
        <BottomNavigation
          showLabels
          value={value}
          onChange={(event, newValue) => {
            setValue(newValue);
          }}
        >
          <BottomNavigationAction
            label="메인화면"
            value={value}
            onClick={() => navigate("/app")}
            icon={<HomeIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="QR생성"
            value={value}
            onClick={() => navigate("/app/qrmaker")}
            icon={<QrCodeIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="QR스캔"
            value={value}
            onClick={() => navigate("/app/qrreader")}
            icon={<QrCodeScannerIcon fontSize="large" />}
          />
          <BottomNavigationAction
            label="내 정보"
            value={value}
            onClick={() => navigate("/app/userinfo")}
            icon={<AccountCircleIcon fontSize="large" />}
          />
        </BottomNavigation>
      </Paper>
    );
  }
};

export default Nav;
