# Automated Teller Machine 
Simple ATM program written in Java

## Install and run 
``` bash
# clone the repo
$ cd <directory>
$ git clone https://github.com/oanhgle/atm.git
# run
$ java -jar atm/build/atm.jar
```

Example data of customers

| Name          | Card Number   | PIN    | Checking Balance | Savings Balance | Savings Activity |
| ------------- |:-------------:| :-----:| :---------------:|:---------------:|:----------------:|
| Shiba Inu     | `123456789`   | `1234` | `$500`           | `$200`          | `2`              |
| Corgi         | `100000001`   | `1000` | `$100`           | `$700`          | `3`              | 
| Husky         | `101010101`   | `1010` | `$1500`          | `$2500`         | `5`              |
| Pom           | `111111111`   | `1111` | `$50`            | `not exist (-1)`| `0`              |
| Chihuahua     | `121212121`   | `1212` | `$150`           | `$250`          | `1`              |

Example output:

``` bash
🐶---------------------------🐶
|       Hello Shiba Inu      |
|  Welcome to Bank of Canine |
🐶---------------------------🐶
  Please choose a transaction:
(1) Check Balance
(2) Transfer Funds
(3) Withdraw Money
(4) Deposit Money
```
