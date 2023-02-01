import * as React from "react";
import Card from "@mui/material/Card";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import { useState, useEffect } from "react";
import QRCode from "react-qr-code";
import { Grid } from "@mui/material";
import CssBaseline from "@mui/material/CssBaseline";

export default function KioskMain() {
  const initialValue = "https://www.example.com";
  const [value, setValue] = useState(initialValue);
  const [countdown, setCountdown] = useState(59);
  const ref = React.useRef(null);

  useEffect(() => {
    if (countdown === 0) {
      setValue(`${initialValue}}`);
      setCountdown(59);
    } else {
      const intervalId = setInterval(() => {
        setCountdown(countdown - 1);
      }, 1000);
      return () => clearInterval(intervalId);
    }
  }, [countdown]);

  React.useEffect(() => {
    ref.current.ownerDocument.body.scrollTop = 0;
  });

  return (
    <Box>
      <Card sx={{ maxWidth: 700, minHeight: 1280 }}>
        <Box sx={{ pb: 7 }} ref={ref}>
          <Card
            sx={{
              fontSize: 33,
              padding: 2,
              textAlign: "center",
              backgroundColor: "#ff8c8c",
              fontWeight: "bold",
            }}
          >
            하이쇼핑
          </Card>
          <Grid
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Grid item xs={10} sx={{ mt: 8 }}>
              <CssBaseline />
              <Card sx={{ border: 1, padding: 1 }}>
                <CardMedia
                  component="img"
                  alt="howtowuse"
                  height="500"
                  image="/kiosk/images/howtouse.jpg"
                />
              </Card>
            </Grid>
          </Grid>
          <Grid
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
          >
            <Grid item xs={10}>
              <Card sx={{ marginTop: 8, padding: 1 }}>
                <QRCode value={value} size="100%" />
              </Card>
              <Card sx={{ textAlign: "center", mb: 1 }}>
                <Button
                  onClick={() => {
                    setValue(`${initialValue}`);
                    setCountdown(59);
                  }}
                  sx={{ fontWeight: "bold" }}
                >
                  QR 재생성
                </Button>
              </Card>
              <Card sx={{ textAlign: "center", mb: 1, fontWeight: "bold" }}>
                자동 재생성까지 {countdown}초
              </Card>
            </Grid>
          </Grid>
          <Grid
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
            }}
            sx={{mt: 5, fontSize: 30, fontWeight:'bold', color:'red'}}
          >
            바코드에 QR을 찍거나 앱으로 QR스캔하세요
          </Grid>
        </Box>
      </Card>
    </Box>
  );
}
