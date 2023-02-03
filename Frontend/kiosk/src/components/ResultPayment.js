import React, { useEffect, useState } from "react";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Avatar from "@mui/material/Avatar";
import { useNavigate } from "react-router-dom" 

const theme = createTheme();


export default function ResultPayment() {
  const navigate = useNavigate();
  const [countdown, setCountdown] = useState(3);

  useEffect(() => {
    const timer = setTimeout(() => {
      setCountdown(countdown - 1);
    }, 1000);
    if (countdown === 0) {
      navigate("/kiosk");
    }
    return () => {
      clearTimeout(timer);
    };
  }, [countdown, navigate]);

  return (
    <ThemeProvider theme={theme}>
      <Container>
        <Box
          sx={{
            height: "100vh",
            width: "1",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Avatar src="/kiosk/images/logo.png" sx={{ mb: 2 }} variant="square" />
          <Typography component="h1" variant="h3">
            결제가
          </Typography>
          <Typography component="h1" variant="h3" sx={{ mb: 2 }}>
            완료되었습니다
          </Typography>
          <Typography component="h1" variant="h5">
            {countdown}초 후 메인화면으로 이동합니다 이용해주셔서 감사합니다
          </Typography>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
