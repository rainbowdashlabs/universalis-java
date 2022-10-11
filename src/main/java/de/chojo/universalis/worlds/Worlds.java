/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import de.chojo.universalis.entities.World;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing the currently valid regions, datacenters and worlds.
 */
public class Worlds {
    private static final Map<Integer, World> ids = new HashMap<>();
    private static final Europe europe = new Europe();
    private static final NorthAmerica northAmerica = new NorthAmerica();
    private static final Oceania oceania = new Oceania();
    private static final Japan japan = new Japan();

    /**
     * The european region
     *
     * @return europe region
     */
    public static Europe europe() {
        return europe;
    }

    /**
     * The north american region
     *
     * @return north american region
     */
    public static NorthAmerica northAmerica() {
        return northAmerica;
    }

    /**
     * The oceanian region
     *
     * @return oceanian region
     */
    public static Oceania oceania() {
        return oceania;
    }

    /**
     * The japanese region
     *
     * @return japanese region
     */
    public static Japan japan() {
        return japan;
    }

    /**
     * Returns all valid regions
     *
     * @return unmodifiable list of regions
     */
    public static List<Region> regions() {
        return List.of(europe, northAmerica, oceania, japan);
    }

    /**
     * Gets a world by its id.
     *
     * @param id world id
     * @return world if valid, otherwise null
     */
    public static World byId(int id) {
        return ids.computeIfAbsent(id, Worlds::findById);
    }

    private static World findById(int id) {
        for (Region region : regions()) {
            for (World world : region.worlds()) {
                if (world.id() == id) return world;
            }
        }
        return null;
    }

    /**
     * The european region
     */
    public static final class Europe implements Region {
        private final Chaos chaos = new Chaos();
        private final Light light = new Light();

        private Europe() {
        }

        /**
         * The chaos datacenter
         *
         * @return chaos datacenter
         */
        public Chaos chaos() {
            return chaos;
        }

        /**
         * The light datacenter
         *
         * @return light datacenter
         */
        public Light light() {
            return light;
        }

        @Override
        public List<Datacenter> datacenters() {
            return List.of(chaos, light);
        }

        /**
         * The chaos datacenter
         */
        public static final class Chaos implements Datacenter {
            /**
             * The world Cerberus
             */
            public final World CERBERUS = World.of("Cerberus", 80);
            /**
             * The world Louisoix
             */
            public final World LOUISOIX = World.of("Louisoix", 83);
            /**
             * The world Moogle
             */
            public final World MOOGLE = World.of("Moogle", 71);
            /**
             * The world Omega
             */
            public final World OMEGA = World.of("Omega", 39);
            /**
             * The world Phantom
             */
            public final World PHANTOM = World.of("Phantom", 401);
            /**
             * The world Ragnarok
             */
            public final World RAGNAROK = World.of("Ragnarok", 97);
            /**
             * The world Sagittarius
             */
            public final World SAGITTARIUS = World.of("Sagittarius", 400);
            /**
             * The world Spriggan
             */
            public final World SPRIGGAN = World.of("Spriggan", 85);

            private Chaos() {
            }

            @Override
            public List<World> worlds() {
                return List.of(CERBERUS, LOUISOIX, MOOGLE, OMEGA, PHANTOM, RAGNAROK, SAGITTARIUS, SPRIGGAN);
            }
        }

        /**
         * The light datacenter
         */
        public static final class Light implements Datacenter {
            /**
             * The world Alpha
             */
            public final World ALPHA = World.of("Alpha", 402);
            /**
             * The world Lich
             */
            public final World LICH = World.of("Lich", 36);
            /**
             * The world Odin
             */
            public final World ODIN = World.of("Odin", 66);
            /**
             * The world Phoenix
             */
            public final World PHOENIX = World.of("Phoenix", 56);
            /**
             * The world Raiden
             */
            public final World RAIDEN = World.of("Raiden", 403);
            /**
             * The world Shiva
             */
            public final World SHIVA = World.of("Shiva", 67);
            /**
             * The world Twintania
             */
            public final World TWINTANIA = World.of("Twintania", 33);
            /**
             * The world Zodiark
             */
            public final World ZODIARK = World.of("Zodiark", 42);

            private Light() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ALPHA, LICH, ODIN, PHOENIX, RAIDEN, SHIVA, TWINTANIA, ZODIARK);
            }
        }
    }

    /**
     * The north american region
     */
    public static final class NorthAmerica implements Region {
        private final Aether aether = new Aether();
        private final Crystal crystal = new Crystal();
        private final Primal primal = new Primal();

        private NorthAmerica() {
        }

        /**
         * The aether datacenter
         *
         * @return aether datacenter
         */
        public Aether aether() {
            return aether;
        }

        /**
         * The crystal datacenter
         *
         * @return crystal datacenter
         */
        public Crystal crystal() {
            return crystal;
        }

        /**
         * The primal datacenter
         *
         * @return primal datacenter
         */
        public Primal primal() {
            return primal;
        }

        @Override
        public List<Datacenter> datacenters() {
            return List.of(aether, crystal, primal);
        }

        /**
         * The aether datacenter
         */
        public static final class Aether implements Datacenter {
            /**
             * The world Adamantoise
             */
            public final World ADAMANTOISE = World.of("Adamantoise", 73);
            /**
             * The world Cactuar
             */
            public final World CACTUAR = World.of("Cactuar", 79);
            /**
             * The world Faerie
             */
            public final World FAERIE = World.of("Faerie", 54);
            /**
             * The world Gilgamesh
             */
            public final World GILGAMESH = World.of("Gilgamesh", 63);
            /**
             * The world Jenova
             */
            public final World JENOVA = World.of("Jenova", 40);
            /**
             * The world Midgardsormr
             */
            public final World MIDGARDSORMR = World.of("Midgardsormr", 65);
            /**
             * The world Sargatanas
             */
            public final World SARGATANAS = World.of("Sargatanas", 99);
            /**
             * The world Siren
             */
            public final World SIREN = World.of("Siren", 57);

            private Aether() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ADAMANTOISE, CACTUAR, FAERIE, GILGAMESH, JENOVA, MIDGARDSORMR, SARGATANAS, SIREN);
            }
        }

        /**
         * The crystal datacenter
         */
        public static final class Crystal implements Datacenter {
            /**
             * The world Balmung
             */
            public final World BALMUNG = World.of("Balmung", 91);
            /**
             * The world Brynhildr
             */
            public final World BRYNHILDR = World.of("Brynhildr", 34);
            /**
             * The world Coeurl
             */
            public final World COEURL = World.of("Coeurl", 74);
            /**
             * The world Diabolos
             */
            public final World DIABOLOS = World.of("Diabolos", 62);
            /**
             * The world Goblin
             */
            public final World GOBLIN = World.of("Goblin", 81);
            /**
             * The world Malboro
             */
            public final World MALBORO = World.of("Malboro", 75);
            /**
             * The world Mateus
             */
            public final World MATEUS = World.of("Mateus", 37);
            /**
             * The world Zalera
             */
            public final World ZALERA = World.of("Zalera", 41);

            private Crystal() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BALMUNG, BRYNHILDR, COEURL, DIABOLOS, GOBLIN, MALBORO, MATEUS, ZALERA);
            }
        }

        /**
         * The primal datacenter
         */
        public static final class Primal implements Datacenter {
            /**
             * The world Behemoth
             */
            public final World BEHEMOTH = World.of("Behemoth", 78);
            /**
             * The world Excalibur
             */
            public final World EXCALIBUR = World.of("Excalibur", 93);
            /**
             * The world Exodus
             */
            public final World EXODUS = World.of("Exodus", 53);
            /**
             * The world Famfrit
             */
            public final World FAMFRIT = World.of("Famfrit", 35);
            /**
             * The world Hyperion
             */
            public final World HYPERION = World.of("Hyperion", 95);
            /**
             * The world Lamia
             */
            public final World LAMIA = World.of("Lamia", 55);
            /**
             * The world Leviathan
             */
            public final World LEVIATHAN = World.of("Leviathan", 64);
            /**
             * The world Ultros
             */
            public final World ULTROS = World.of("Ultros", 77);

            private Primal() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BEHEMOTH, EXCALIBUR, EXODUS, FAMFRIT, HYPERION, LAMIA, LEVIATHAN, ULTROS);
            }
        }
    }

    /**
     * The oceanian region
     */
    public static final class Oceania implements Region {
        private final Materia materia = new Materia();

        private Oceania() {
        }

        /**
         * The materia datacenter
         *
         * @return materia datacenter
         */
        public Materia materia() {
            return materia;
        }

        @Override
        public List<Datacenter> datacenters() {
            return Collections.singletonList(materia);
        }

        /**
         * The materia datacenter
         */
        public static final class Materia implements Datacenter {
            /**
             * The world Bismarck
             */
            public final World BISMARCK = World.of("Bismarck", 22);
            /**
             * The world Ravana
             */
            public final World RAVANA = World.of("Ravana", 21);
            /**
             * The world Sephirot
             */
            public final World SEPHIROT = World.of("Sephirot", 86);
            /**
             * The world Sophia
             */
            public final World SOPHIA = World.of("Sophia", 87);
            /**
             * The world Zurvan
             */
            public final World ZURVAN = World.of("Zurvan", 88);

            private Materia() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BISMARCK, RAVANA, SEPHIROT, SOPHIA, ZURVAN);
            }
        }
    }

    /**
     * The japanese region
     */
    public static final class Japan implements Region {
        private final Elemental elemental = new Elemental();
        private final Gaia gaia = new Gaia();
        private final Mana mana = new Mana();
        private final Meteor meteor = new Meteor();

        private Japan() {
        }

        @Override
        public List<Datacenter> datacenters() {
            return List.of(elemental, gaia, mana, meteor);
        }


        /**
         * The elemental datacenter
         *
         * @return elemental datacenter
         */
        public Elemental elemental() {
            return elemental;
        }

        /**
         * The gaia datacenter
         *
         * @return gaia datacenter
         */
        public Gaia gaia() {
            return gaia;
        }

        /**
         * The mana datacenter
         *
         * @return mana datacenter
         */
        public Mana mana() {
            return mana;
        }

        /**
         * The meteor datacenter
         *
         * @return meteor datacenter
         */
        public Meteor meteor() {
            return meteor;
        }

        /**
         * The elemental datacenter
         */
        public static final class Elemental implements Datacenter {
            /**
             * The world Aegis
             */
            public final World AEGIS = World.of("Aegis", 90);
            /**
             * The world Atomos
             */
            public final World ATOMOS = World.of("Atomos", 68);
            /**
             * The world Carbuncle
             */
            public final World CARBUNCLE = World.of("Carbuncle", 45);
            /**
             * The world Garuda
             */
            public final World GARUDA = World.of("Garuda", 58);
            /**
             * The world Gungnir
             */
            public final World GUNGNIR = World.of("Gungnir", 94);
            /**
             * The world Kujata
             */
            public final World KUJATA = World.of("Kujata", 49);
            /**
             * The world Tonberry
             */
            public final World TONBERRY = World.of("Tonberry", 72);
            /**
             * The world Typhon
             */
            public final World TYPHON = World.of("Typhon", 50);

            private Elemental() {
            }

            @Override
            public List<World> worlds() {
                return List.of(AEGIS, ATOMOS, CARBUNCLE, GARUDA, GUNGNIR, KUJATA, TONBERRY, TYPHON);
            }
        }

        /**
         * The gaia datacenter
         */
        public static final class Gaia implements Datacenter {
            /**
             * The world Alexander
             */
            public final World ALEXANDER = World.of("Alexander", 43);
            /**
             * The world Bahamut
             */
            public final World BAHAMUT = World.of("Bahamut", 69);
            /**
             * The world Durandal
             */
            public final World DURANDAL = World.of("Durandal", 92);
            /**
             * The world Fenrir
             */
            public final World FENRIR = World.of("Fenrir", 46);
            /**
             * The world Ifrit
             */
            public final World IFRIT = World.of("Ifrit", 59);
            /**
             * The world Ridill
             */
            public final World RIDILL = World.of("Ridill", 98);
            /**
             * The world Tiamat
             */
            public final World TIAMAT = World.of("Tiamat", 76);
            /**
             * The world Ultima
             */
            public final World ULTIMA = World.of("Ultima", 51);

            private Gaia() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ALEXANDER, BAHAMUT, DURANDAL, FENRIR, IFRIT, RIDILL, TIAMAT, ULTIMA);
            }
        }

        /**
         * The mana datacenter
         */
        public static final class Mana implements Datacenter {
            /**
             * The world Anima
             */
            public final World ANIMA = World.of("Anima", 44);
            /**
             * The world Asura
             */
            public final World ASURA = World.of("Asura", 23);
            /**
             * The world Chocobo
             */
            public final World CHOCOBO = World.of("Chocobo", 70);
            /**
             * The world Hades
             */
            public final World HADES = World.of("Hades", 47);
            /**
             * The world Ixion
             */
            public final World IXION = World.of("Ixion", 48);
            /**
             * The world Masamune
             */
            public final World MASAMUNE = World.of("Masamune", 96);
            /**
             * The world Pandaemonium
             */
            public final World PANDAEMONIUM = World.of("Pandaemonium", 28);
            /**
             * The world Titan
             */
            public final World TITAN = World.of("Titan", 61);

            private Mana() {
            }

            @Override
            public List<World> worlds() {
                return List.of(ANIMA, ASURA, CHOCOBO, HADES, IXION, MASAMUNE, PANDAEMONIUM, TITAN);
            }
        }

        /**
         * The elemental datacenter
         */
        public static final class Meteor implements Datacenter {
            /**
             * The world Belias
             */
            public final World BELIAS = World.of("Belias", 24);
            /**
             * The world Mandragora
             */
            public final World MANDRAGORA = World.of("Mandragora", 82);
            /**
             * The world Ramuh
             */
            public final World RAMUH = World.of("Ramuh", 60);
            /**
             * The world Shinryu
             */
            public final World SHINRYU = World.of("Shinryu", 29);
            /**
             * The world Unicorn
             */
            public final World UNICORN = World.of("Unicorn", 30);
            /**
             * The world Valefor
             */
            public final World VALEFOR = World.of("Valefor", 52);
            /**
             * The world Yojimbo
             */
            public final World YOJIMBO = World.of("Yojimbo", 31);
            /**
             * The world Zeromus
             */
            public final World ZEROMUS = World.of("Zeromus", 32);

            private Meteor() {
            }

            @Override
            public List<World> worlds() {
                return List.of(BELIAS, MANDRAGORA, RAMUH, SHINRYU, UNICORN, VALEFOR, YOJIMBO, ZEROMUS);
            }
        }
    }
}
