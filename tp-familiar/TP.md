# TP Familiar

Support de cours et TPs : https://www.dropbox.com/sh/5cbvugqe3oz1fwk/AACR2m1TsuY8pY7aq__5YAQpa?dl=0 

### Question 0

Feature model :
```
fm1 = FM(CE: H C DAS; H: (ACFAR|AC); C : [AHL]; DAS : (SAC|FFL)+; (!AHL|FFL); (!(SAC&FFL)|ACFAR); )
s1 = configs fm1
c1 = counting fm1
core1 = cores fm1
```

### Question 1

Liste des posibilités (c1) :
```
{{FFL;ACFAR;H;C;CE;DAS};{C;DAS;AC;H;CE;FFL};{AHL;H;CE;DAS;FFL;ACFAR;C};{C;DAS;H;ACFAR;CE;FFL;SAC};{DAS;ACFAR;FFL;AHL;CE;H;C;SAC};{DAS;CE;AC;C;H;SAC};{C;DAS;H;SAC;ACFAR;CE};{CE;FFL;AHL;AC;DAS;C;H}}
```

### Question 2
Features qui sont incluses dans n’importe quelle configuration :
```
core = {DAS;H;CE;C}
```

### Question 5
```
fm2 = FM (A : [B] [C]; (!B|!C); (B&C); )
c2 = counting fm2
valid2 = isValid fm2
```

c2 vaut 0 et valid2 vaut false

### Question 6
- Sans la première contrainte (B => !C) : C'est valide
- Sans la deuxième contrainte (B ^ C) : C'est valide
C'est normal, l'une est le contraire de l'autre

### Question 7
```
fm3 = FM(R: A I M; A: [D] B [C]; I: J L [K]; M: (P|N|O)+; D: (E|F); !C|!E; !J|C; )
```

Liste des posibilités :
```
s3 = {{A;M;B;R;J;I;O;C;P;L};{L;A;C;B;D;R;K;O;J;I;M;F};{D;C;L;F;K;B;M;A;I;R;N;P;J};{L;N;R;C;P;M;I;J;A;B};{N;C;O;R;A;B;I;L;J;M};{J;A;R;B;I;M;N;L;C};{R;M;B;C;J;O;K;A;I;L};{J;K;M;F;B;P;I;L;C;D;A;O;R};{B;A;P;R;C;I;L;M;J};{R;L;P;M;B;I;K;C;A;N;J};{J;R;K;M;O;I;N;B;C;A;L};{O;A;I;D;L;R;J;P;B;M;C;F;N};{R;K;F;J;B;M;D;C;P;I;A;L};{I;J;A;L;M;N;R;C;P;O;B};{D;L;R;A;N;K;M;F;I;J;B;C};{O;M;C;I;J;L;A;B;R};{K;R;M;N;C;O;B;P;J;I;L;A};{I;M;O;L;A;B;C;F;P;R;J;D};{A;B;O;F;J;R;D;I;N;L;M;K;C};{P;J;B;A;D;F;R;C;I;L;M};{C;I;B;A;D;J;O;R;L;M;F;N};{M;A;L;B;J;R;I;C;K;N};{P;I;K;M;A;R;B;C;J;L};{N;C;L;M;B;I;J;F;D;A;R};{B;C;F;L;N;J;D;A;I;P;R;M};{C;D;J;O;R;F;A;B;I;L;M};{P;B;K;O;A;J;L;M;R;I;C};{D;O;F;J;L;M;K;C;P;R;I;B;A;N}}
```

- Que peut-on dire sur les features C et F ? 
	- A cause de la contrainte C => !E, et le XOR entre E et F, on en déduit C => F
Que peut-on dire sur la feature E ?
	- On a jamais E, car J est obligatoire


```
DEAD = E









