% Numero de variables
int: numVars;

% Numero de restricciones
int: numConstrains;

% Vector de variables 
array[1..numVars] of var 0..1: x;

% Vector de costo
array[1..numVars] of int: costs;

% Matriz de restricciones
array[1..numConstrains, 1..numVars] of int : constraints;

% Funcion objetivo
  var int : z = sum ([ x[j] * costs[j] | j in 1..numVars ]);
solve minimize z; 

% Restriccion
constraint
  forall(i in 1..numConstrains) (
    forall(j in 1..numVars) (
      sum([x[j] | j in 1..numVars where constraints[i,j] == 1]) >= 1
    )
  );

% Definir la instancia
numConstrains = 5;

numVars = 6;

costs = array1d (1..numVars, [
  65,55,60,45,40,50
]);

constraints = array2d(1..numConstrains, 1..numVars, [
  1,1,0,0,0,0,
  1,1,0,0,0,1,
  0,0,1,1,1,0,
  0,0,0,1,0,1,
  0,1,0,0,1,1
]);

output ["x = " ++show(x)++ ", z = " ++show(z)++ "\n"];
