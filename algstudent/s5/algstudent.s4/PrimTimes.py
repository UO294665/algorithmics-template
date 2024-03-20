from Prim import Prim_Function
from Helper import triangularMatrixRandomIntegers
from time import time
import random

n = 256
for i in range(10):
    print ("TIME (milliseconds)")
    t1=0
    t2=0
    m = triangularMatrixRandomIntegers(n, 4, 150)
    t1 = time()
    Prim_Function(m)
    t2 = time()
    print("n= ",n,"**time=",int(1000*(t2-t1)))
    n = n*2
