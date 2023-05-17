float myAtof(char* string, char* error) {
    float result = 0.0;
    int decimal = 0;
    int sign = 1;
    int i = 0;

    // Check for negative sign
    if (string[0] == '-') {
        sign = -1;
        i++;
    }

    // Process digits before decimal point
    while (string[i] != '\0' && string[i] != '.') {
        if (string[i] >= '0' && string[i] <= '9') {
            // Check for overflow
            if (result > (FLT_MAX - (string[i] - '0')) / 10.0) {
                *error = 1;
                return 0.0;
            }
            result = result * 10.0 + (string[i] - '0');
        } else {
            *error = 1;
            return 0.0;
        }
        i++;
    }

    // Process digits after decimal point
    if (string[i] == '.') {
        decimal = 1;
        i++;
    }
    while (string[i] != '\0') {
        if (string[i] >= '0' && string[i] <= '9') {
            // Check for overflow
            if (result > (FLT_MAX - (string[i] - '0') / powf(10, decimal))) {
                *error = 1;
                return 0.0;
            }
            result = result * 10.0 + (string[i] - '0');
            decimal++;
        } else {
            *error = 1;
            return 0.0;
        }
        i++;
    }

    // Calculate final result
    result = sign * result / powf(10, decimal);

    return result;
}
