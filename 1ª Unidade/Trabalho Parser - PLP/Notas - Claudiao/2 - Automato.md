
#em Graphviz 
#https://edotor.net/

	digraph DFA {
	    rankdir=LR;
	    node [shape=circle];
	    
	    q0 [shape=doublecircle];
	    q0 [label="q0 (inicial)"];
	    q1 [label="q1"];
	    q2 [label="q2"];
	    q3 [label="q3"];
	    q4 [label="q4"];
	    q5 [label="q5"];
	    
	    q0 -> q1 [label="identificador"];
	    q1 -> q2 [label="="];
	    q2 -> q3 [label="número"];
	    q3 -> q0 [label=";"];
	    
	    q0 -> q4 [label="print"];
	    q4 -> q5 [label="identificador"];
	    q5 -> q0 [label=";"];
	}