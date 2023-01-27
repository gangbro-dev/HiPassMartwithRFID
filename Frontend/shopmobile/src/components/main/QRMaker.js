import React, { useState, useEffect } from "react";
import QRCode from "react-qr-code";
import { Box, Card } from "@mui/material";
import { Grid } from "@mui/material";
import CssBaseline from "@mui/material/CssBaseline";

function Test() {
  const initialValue = "https://www.example.com";
  const [value, setValue] = useState(initialValue);
  const [countdown, setCountdown] = useState(59);
  const ref = React.useRef(null);

  useEffect(() => {
    if (countdown === 0) {
      setValue(`https://www.example.com/${Math.random()}`);
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
      <Grid container spacing={2}>
        <Grid item xs={1} />
        <Grid item xs={10} mt={4}>
          <CssBaseline />
          <Card sx={{ border: 1 }}>
            <div>
              <QRCode value={value} size='100%'/>
              <div>
                <button
                  onClick={() => {
                    setValue(`https://www.example.com/${Math.random()}`);
                    setCountdown(59);
                  }}
                >
                  Regenerate QR Code
                </button>
                <div>QR Code will regenerate in {countdown} seconds</div>
              </div>
            </div>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
}

export default Test;
