import React, { useState } from "react";
import QrReader from "modern-react-qr-reader";
import { Box, Card } from "@mui/material";
import { Grid } from "@mui/material";
import CssBaseline from "@mui/material/CssBaseline";

const Test = (props) => {
  const [result, setResult] = useState("No result");
  const ref = React.useRef(null);

  const handleScan = (data) => {
    if (data) {
      setResult(data);
    }
  };

  const handleError = (err) => {
    console.error(err);
  };

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
            <QrReader
              delay={300}
              facingMode={"environment"}
              onError={handleError}
              onScan={handleScan}
            />
            <p>{result}</p>
          </Card>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Test;
