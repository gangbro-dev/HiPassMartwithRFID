import React from "react";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Avatar from "@mui/material/Avatar";

const theme = createTheme();
export default function ResultPayment() {
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
          <Avatar src="/kiosk/images/logo.png" sx={{ mb: 2 }} />
          <Typography component="h1" variant="h3">
            결제가
          </Typography>
          <Typography component="h1" variant="h3" sx={{ mb: 2 }}>
            완료되었습니다
          </Typography>
          <Typography component="h1" variant="h5">
            이용해주셔서 감사합니다
          </Typography>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
