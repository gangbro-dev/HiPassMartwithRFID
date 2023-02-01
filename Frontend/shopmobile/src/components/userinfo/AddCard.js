import React, { useState } from "react";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  FormControl,
  FormControlLabel,
  Checkbox,
  FormHelperText,
  Grid,
  Box,
  Typography,
  Container,
  Radio,
  RadioGroup,
  FormLabel,
  Link,
} from "@mui/material/";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import styled from "styled-components";
import HOST from "../../Host";
import { useNavigate } from "react-router";

const FormHelperTexts = styled(FormHelperText)`
  width: 100%;
  padding-left: 16px;
  font-weight: 700 !important;
  color: #d32f2f !important;
`;


const Boxs = styled(Box)`
  padding-bottom: 40px !important;
`;


const Register = () => {

  const ref = React.useRef(null);

  React.useEffect(() => {
    ref.current.ownerDocument.body.scrollTop = 0;
  });



  const [cardNumber, setCardNumber] = useState("");
  const [cardExpiration, setCardExpiration] = useState("");
  const [cardCompany, setCardCompany] = useState("");
  const [error, setError] = useState({
    cardNumber: false,
    cardExpiration: false,
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    if (cardNumber === "" || cardExpiration === "") {
      setError({
        cardNumber: cardNumber === "",
        cardExpiration: cardExpiration === "",
      });
    } else {
      // Make API call to register card
      console.log("Card Registered!");
    }
  };

  return (
    <Box sx={{ pb: 7 }} ref={ref}>
      <ThemeProvider>
        <Container maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Avatar src="./images/logo.png" variant="square" sx={{ mb: 2 }} />
            <Typography component="h1" variant="h5">
              회원가입
            </Typography>
            <Boxs
              component="form"
              noValidate
              onSubmit={handleSubmit}
              sx={{ mt: 3 }}
            >
              <FormControl>
                
              <Container>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <TextField
              error={error.cardNumber}
              required
              fullWidth
              id="cardNumber"
              label="카드번호"
              value={cardNumber}
              onChange={(e) => setCardNumber(e.target.value)}
            />
            {error.cardNumber && (
              <FormHelperText error>This field is required</FormHelperText>
            )}
          </Grid>
          <Grid item xs={12}>
            <TextField
              error={error.cardExpiration}
              required
              fullWidth
              id="cardExpiration"
              label="유효기간"
              value={cardExpiration}
              onChange={(e) => setCardExpiration(e.target.value)}
            />
            {error.cardExpiration && (
              <FormHelperText error>This field is required</FormHelperText>
            )}
          </Grid>
          <Grid item xs={12}>
            <FormControl fullWidth>
              <FormLabel>카드회사</FormLabel>
              <RadioGroup
                value={cardCompany}
                onChange={(e) => setCardCompany(e.target.value)}
                >
                <FormControlLabel value="신한" control={<Radio />} label="신한" />
                <FormControlLabel value="현대" control={<Radio />} label="현대" />
                <FormControlLabel value="ibk" control={<Radio />} label="ibk" />
                <FormControlLabel value="하나" control={<Radio />} label="하나" />
                <FormControlLabel value="우리" control={<Radio />} label="우리" />
              </RadioGroup>
            </FormControl>
          </Grid>
          <Grid item xs={12}>
            <Box display="flex" justifyContent="flex-end">
              <button type="submit">Register</button>
            </Box>
          </Grid>
        </Grid>
      </form>
    </Container>



                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 1, mb: 2 }}
                  size="large"
                >
                  회원가입
                </Button>
              </FormControl>
              <Grid container justifyContent="flex-end">
                <Grid item>
                  <Link href="/app/login" variant="body2">
                    로그인으로
                  </Link>
                </Grid>
              </Grid>
            </Boxs>
          </Box>
        </Container>
      </ThemeProvider>
    </Box>


    
  );
};

export default Register;
