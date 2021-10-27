[,,...x]=process.argv;if(x.length!=0)for(j=x[0].length;j>0;j--)for(l=0;l<=x[0].length-j;){a=x[0].slice(l,l+++j);for(i of x)if(i.search(a)==-1)a=null;if(a){console.log(a);process.exit()}}
