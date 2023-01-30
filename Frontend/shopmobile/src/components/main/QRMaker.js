import React, { useState, useEffect } from "react";
import QRCode from "react-qr-code";
import { Box, Button, Card } from "@mui/material";
import { Grid } from "@mui/material";
import CssBaseline from "@mui/material/CssBaseline";

function Test() {
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
    <Box sx={{ pb: 7 }} ref={ref}>
      <Card
        sx={{
          fontSize: 33,
          padding: 2,
          textAlign: "center",
          backgroundColor: "#64b5f6",
          fontWeight: "bold",
        }}
      >
        QR생성
      </Card>
      <Grid container spacing={2}>
        <Grid item xs={1} />
        <Grid item xs={10} mt={15}>
          <CssBaseline />
          <Card 
          sx={{ border: 1, padding: 1, maxWidth: 450 }}>
            <div>
              <QRCode value={value} size="100%" />
            </div>
            <Button
              onClick={() => {
                setValue(`https://www.example.com/${Math.random()}`);
                setCountdown(59);
              }}
              sx={{}}
            >
              바로 재생성
            </Button>
            <Card sx={{ textAlign: "center" }}>
              자동 재생성까지 {countdown}초
            </Card>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
}

export default Test;
