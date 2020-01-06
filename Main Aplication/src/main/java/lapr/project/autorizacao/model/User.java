package lapr.project.autorizacao.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {

    private final String strName;
    private final String strEmail;
    private final String strPassword;
    private final Set<UserPapers> lstPapeis = new HashSet<>();

    public User(String strNome, String strEmail, String strPassword) {
        if ((strNome == null) || (strEmail == null) || (strPassword == null) || (strNome.isEmpty()) || (strEmail.isEmpty()) || (strPassword.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos não pode ser nulo ou vazio.");
        }
        this.strName = strNome;
        this.strEmail = strEmail;
        this.strPassword = strPassword;
    }

    public String getId() {
        return this.strEmail;
    }

    public String getNome() {
        return this.strName;
    }

    public String getEmail() {
        return this.strEmail;
    }

    public boolean hasId(String strId) {
        return this.strEmail.equals(strId);
    }

    public boolean hasPassword(String strPwd) {
        return this.strPassword.equals(strPwd);
    }

    public boolean addPapel(UserPapers papel) {
        if (papel != null) {
            return this.lstPapeis.add(papel);
        }
        return false;
    }

    public boolean removePapel(UserPapers papel) {
        if (papel != null) {
            return this.lstPapeis.remove(papel);
        }
        return false;
    }

    public boolean hasPapel(UserPapers papel) {
        return this.lstPapeis.contains(papel);
    }

    public boolean hasPapel(String strPapel) {
        for (UserPapers papel : this.lstPapeis) {
            if (papel.hasId(strPapel)) {
                return true;
            }
        }
        return false;
    }

    public List<UserPapers> getPapeis() {
        List<UserPapers> list = new ArrayList<>();
        for (UserPapers papel : this.lstPapeis) {
            list.add(papel);
        }
        return list;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.strEmail);
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
        User obj = (User) o;
        return Objects.equals(strEmail, obj.strEmail);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.strName, this.strEmail);
    }
}
