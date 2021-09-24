package br.iesb.navigatorapi.repository;

import br.iesb.navigatorapi.model.NavigatorEntity;

public class NavigatorRepo {

    ArrayList<NavigatorEntity> Users = new ArrayList<NavigatorEntity>();

    public NavigatorEntity userProfile() {
        return this.Users;
    }

}
