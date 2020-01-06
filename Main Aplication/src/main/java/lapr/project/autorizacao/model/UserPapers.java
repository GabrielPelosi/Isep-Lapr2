package lapr.project.autorizacao.model;

import java.util.Objects;

public class UserPapers {

    private final String strPapel;
    private final String strDescription;

    public UserPapers(String strPapel) {
        if ((strPapel == null) || (strPapel.isEmpty())) {
            throw new IllegalArgumentException("The argument can not be null or empty.");
        }
        this.strPapel = strPapel;
        this.strDescription = strPapel;
    }

    public UserPapers(String strPapel, String strDescricao) {
        if ((strPapel == null) || (strDescricao == null) || (strPapel.isEmpty()) || (strDescricao.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can not be null or empty.");
        }

        this.strPapel = strPapel;
        this.strDescription = strDescricao;
    }

    public String getPapel() {
        return this.strPapel;
    }

    public String getDescricao() {
        return this.strDescription;
    }

    public boolean hasId(String strId) {
        return this.strPapel.equals(strId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.strPapel);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        UserPapers obj = (UserPapers) o;
        return Objects.equals(strPapel, obj.strPapel);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.strPapel, this.strDescription);
    }
}
