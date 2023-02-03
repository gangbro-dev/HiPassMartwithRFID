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
  FormLabel,
  Select,
  MenuItem,
} from "@mui/material/";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import styled from "styled-components";

const Boxs = styled(Box)`
  padding-bottom: 40px !important;
`;

const AddCard = () => {
  const ref = React.useRef(null);

  React.useEffect(() => {
    ref.current.ownerDocument.body.scrollTop = 0;
  });

  const [checked, setChecked] = useState(false);
  const [cardNumber, setCardNumber] = useState("");
  const [cardExpiration, setCardExpiration] = useState("");
  const [cardCompany, setCardCompany] = useState("");
  const [error, setError] = useState({
    cardNumber: false,
    cardExpiration: false,
  });

  const theme = createTheme();

  const handleAgree = (event) => {
    setChecked(event.target.checked);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // 회원가입 동의 체크
    if (!checked) alert("회원가입 약관에 동의해주세요.");

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

  const handleCardNumber = (e) => {
    const cardNum = e.target.value;
    if (!/^\d+$/.test(cardNum)) {
      setError({ ...error, cardNumber: true });
    } else {
      setError({ ...error, cardNumber: false });
      setCardNumber(cardNum);
    }
  };

  const handleCardExpiration = (e) => {
    const cardExp = e.target.value;
    if (!/^\d+$/.test(cardExp)) {
      setError({ ...error, cardExpiration: true });
    } else {
      setError({ ...error, cardExpiration: false });
      setCardExpiration(cardExp);
    }
  };

  return (
    <Box sx={{ pb: 7 }} ref={ref}>
      <ThemeProvider theme={theme}>
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
              카드등록
            </Typography>
            <Boxs component="form" noValidate sx={{ mt: 3 }}>
              <FormControl>
                <Grid container spacing={2}>
                  <Container>
                    <Boxs onSubmit={handleSubmit}>
                      <Grid container spacing={2}>
                        <Grid item xs={12}>
                          <TextField
                            error={error.cardNumber}
                            required
                            fullWidth
                            id="cardNumber"
                            label="카드번호"
                            value={cardNumber}
                            onChange={handleCardNumber}
                            inputProps={{ maxLength: 16 }}
                          />
                          {error.cardNumber && (
                            <FormHelperText error>
                              올바른 값을 입력해주세요.
                            </FormHelperText>
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
                            onChange={handleCardExpiration}
                            inputProps={{ maxLength: 4 }}
                          />
                          {error.cardExpiration && (
                            <FormHelperText error>
                              올바른 값을 입력해주세요.
                            </FormHelperText>
                          )}
                        </Grid>
                        <Grid item xs={12}>
                          <FormControl fullWidth>
                            <FormLabel>카드회사</FormLabel>
                            <Select
                              value={cardCompany}
                              onChange={(e) => setCardCompany(e.target.value)}
                            >
                              <MenuItem value="신한">신한</MenuItem>
                              <MenuItem value="현대">현대</MenuItem>
                              <MenuItem value="ibk">ibk</MenuItem>
                              <MenuItem value="하나">하나</MenuItem>
                              <MenuItem value="우리">우리</MenuItem>
                            </Select>
                          </FormControl>
                        </Grid>
                      </Grid>
                    </Boxs>
                  </Container>

                  <Grid item xs={12}>
                    <FormControlLabel
                      control={
                        <Checkbox onChange={handleAgree} color="primary" />
                      }
                      label="개인정보 수집 및 이용에 동의합니다.(필수)"
                    />
                  </Grid>
                </Grid>
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  color="primary"
                  style={{ height: "50px" }}
                  onClick={handleSubmit}
                >
                  카드등록
                </Button>
              </FormControl>
            </Boxs>
          </Box>
        </Container>
      </ThemeProvider>
    </Box>
  );
};

export default AddCard;
