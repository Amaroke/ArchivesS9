--------------------------- MODULE BlockingQueue ---------------------------
(***************************************************************************)
(* Original problem and spec by Michel Charpentier                         *)
(* http://www.cs.unh.edu/~charpov/programming-tlabuffer.html               *)
(***************************************************************************)
EXTENDS Naturals, Sequences

CONSTANTS Producers,   (* the (nonempty) set of producers                       *)
          Consumers,   (* the (nonempty) set of consumers                       *)
          BufCapacity  (* the maximum number of messages in the bounded buffer  *)

ASSUME Assumption ==
       /\ Producers # {}                      (* at least one producer *)
       /\ Consumers # {}                      (* at least one consumer *)
       /\ Producers \intersect Consumers = {} (* no thread is both consumer and producer *)
       /\ BufCapacity \in (Nat \ {0})         (* buffer capacity is at least 1 *)

-----------------------------------------------------------------------------
(*
--algorithm BlockingQueue {
    variables buffer = << >>, waitSet = {};

    define {
        isfull(b) == Len(b) = BufCapacity
        isempty(b) == Len(b) = 0
    }

    macro wait(id) {
        waitSet := waitSet \union {id};
    }

    macro notify() {
        if (waitSet # {}) {
            with (t \in waitSet) {
                waitSet := waitSet \ {t};
            }
        }
    }

    macro put(id) {
        if (isfull(buffer)) {
            wait(id);
        }
        else {
            buffer := Append(buffer, id);
            notify();
        }
    }

    macro get(item) {
        if (isempty(buffer)) {
            wait(item);
        }
        else {
            buffer := Tail(buffer);
            notify();
        }
    }
    

    process (p \in Producers) {
p0:     while (TRUE) {
            await(self \notin waitSet);
            put(self);
        }
    }

    process (c \in Consumers) {
c0:     while (TRUE) {
            await(self \notin waitSet);
            get(self);
       }
    }
}
*)
\* BEGIN TRANSLATION (chksum(pcal) = "6ba7a79a" /\ chksum(tla) = "a9f6c116")
VARIABLES buffer, waitSet

(* define statement *)
isfull(b) == Len(b) = BufCapacity
isempty(b) == Len(b) = 0


vars == << buffer, waitSet >>

ProcSet == (Producers) \cup (Consumers)

Init == (* Global variables *)
        /\ buffer = << >>
        /\ waitSet = {}

p(self) == /\ (self \notin waitSet)
           /\ IF isfull(buffer)
                 THEN /\ waitSet' = (waitSet \union {self})
                      /\ UNCHANGED buffer
                 ELSE /\ buffer' = Append(buffer, self)
                      /\ IF waitSet # {}
                            THEN /\ \E t \in waitSet:
                                      waitSet' = waitSet \ {t}
                            ELSE /\ TRUE
                                 /\ UNCHANGED waitSet

c(self) == /\ (self \notin waitSet)
           /\ IF isempty(buffer)
                 THEN /\ waitSet' = (waitSet \union {self})
                      /\ UNCHANGED buffer
                 ELSE /\ buffer' = Tail(buffer)
                      /\ IF waitSet # {}
                            THEN /\ \E t \in waitSet:
                                      waitSet' = waitSet \ {t}
                            ELSE /\ TRUE
                                 /\ UNCHANGED waitSet

Next == (\E self \in Producers: p(self))
           \/ (\E self \in Consumers: c(self))

Spec == Init /\ [][Next]_vars

\* END TRANSLATION 

=============================================================================
