/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import org.jetbrains.annotations.Contract;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Class containing the currently valid regions, datacenters and worlds.
 */
@SuppressWarnings("unused")
public class Worlds {
    private static final Map<Integer, World> ids = new HashMap<>();
    private static final Map<String, World> names = new HashMap<>();
    private static final Europe europe = new Europe();
    private static final NorthAmerica northAmerica = new NorthAmerica();
    private static final Oceania oceania = new Oceania();
    private static final Japan japan = new Japan();
    private static final China china = new China();

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
     * The 中国 region
     *
     * @return 中国 region
     */
    public static China china() {
        return china;
    }

    /**
     * Returns all valid regions
     *
     * @return unmodifiable list of regions
     */
    public static List<Region> regions() {
        return List.of(europe, northAmerica, oceania, japan, china);
    }

    /**
     * Gets a world by its id.
     *
     * @param id world id
     * @return world if valid or a dummy world with the matching ID
     */
    public static World worldById(int id) {
        return ids.computeIfAbsent(id, Worlds::findWorldById);
    }

    /**
     * Retrieves a data center by name
     *
     * @param name data center name case-insensitive
     * @return data center
     */
    @Nullable
    @Contract("null -> null")
    public static DataCenter datacenterByName(@Nullable String name) {
        for (Region region : regions()) {
            for (DataCenter datacenter : region.datacenters()) {
                if (datacenter.name().equalsIgnoreCase(name)) return datacenter;
            }
        }
        return null;
    }

    /**
     * Retrieves a region by name
     *
     * @param name region name case-insensitive
     * @return region
     */
    @Nullable
    @Contract("null -> null")
    public static Region regionByName(@Nullable String name) {
        for (Region region : regions()) {
            if (region.name().equalsIgnoreCase(name)) return region;
        }
        return null;
    }

    private static World findWorldById(int id) {
        for (Region region : regions()) {
            for (World world : region.worlds()) {
                if (world.id() == id) return world;
            }
        }
        for (World value : names.values()) {
            if (value.id() == id) return value;
        }
        return World.of("", id, null);
    }

    /**
     * Retrieve a world by its name
     *
     * @param name name case-insensitive
     * @return world
     */
    @Nullable
    @Contract("null -> null")
    public static World worldByName(@Nullable String name) {
        if (name == null) return null;
        return names.computeIfAbsent(name.toLowerCase(), Worlds::findWorldByName);
    }

    private static World findWorldByName(String name) {
        for (Region region : regions()) {
            for (World world : region.worlds()) {
                if (world.name().equalsIgnoreCase(name)) return world;
            }
        }
        for (World world : ids.values()) {
            if (world.name().equalsIgnoreCase(name)) return world;
        }
        return World.of(name, -1, null);
    }

    /**
     * Transforms a world entity to an internal richer world model
     *
     * @param world world entity
     * @return world
     */
    public static World toWorld(de.chojo.universalis.entities.World world) {
        World iWorld = World.of(world.name(), world.id(), null);
        World worldByName = findWorldByName(world.name());
        if (worldByName.id() != -1) {
            iWorld = worldByName;
        }

        World worldById = findWorldById(world.id());
        if (!worldById.name().isBlank()) {
            iWorld = worldById;
        }

        if (worldById.name().isBlank()) {
            ids.put(iWorld.id(), iWorld);
        }

        if (worldByName.id() == -1) {
            names.put(iWorld.name().toLowerCase(Locale.ROOT), iWorld);
        }

        return iWorld;
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
         * The chaos data center
         *
         * @return chaos data center
         */
        public Chaos chaos() {
            return chaos;
        }

        /**
         * The light data center
         *
         * @return light data center
         */
        public Light light() {
            return light;
        }

        @Override
        public List<DataCenter> datacenters() {
            return List.of(chaos, light);
        }

        @Override
        public String name() {
            return "Europe";
        }

        /**
         * The chaos data center
         */
        public static final class Chaos implements DataCenter {
            /**
             * The world Cerberus
             */
            public final World cerberus = World.of("Cerberus", 80, this);
            /**
             * The world Louisoix
             */
            public final World louisoix = World.of("Louisoix", 83, this);
            /**
             * The world Moogle
             */
            public final World moogle = World.of("Moogle", 71, this);
            /**
             * The world Omega
             */
            public final World omega = World.of("Omega", 39, this);
            /**
             * The world Phantom
             */
            public final World phantom = World.of("Phantom", 401, this);
            /**
             * The world Ragnarok
             */
            public final World ragnarok = World.of("Ragnarok", 97, this);
            /**
             * The world Sagittarius
             */
            public final World sagittarius = World.of("Sagittarius", 400, this);
            /**
             * The world Spriggan
             */
            public final World spriggan = World.of("Spriggan", 85, this);

            @Override
            public List<World> worlds() {
                return List.of(cerberus, louisoix, moogle, omega, phantom, ragnarok, sagittarius, spriggan);
            }

            @Override
            public int id() {
                return 6;
            }

            @Override
            public Region region() {
                return europe;
            }
        }

        /**
         * The light data center
         */
        public static final class Light implements DataCenter {
            /**
             * The world Alpha
             */
            public final World alpha = World.of("Alpha", 402, this);
            /**
             * The world Lich
             */
            public final World lich = World.of("Lich", 36, this);
            /**
             * The world Odin
             */
            public final World odin = World.of("Odin", 66, this);
            /**
             * The world Phoenix
             */
            public final World phoenix = World.of("Phoenix", 56, this);
            /**
             * The world Raiden
             */
            public final World raiden = World.of("Raiden", 403, this);
            /**
             * The world Shiva
             */
            public final World shiva = World.of("Shiva", 67, this);
            /**
             * The world Twintania
             */
            public final World twintania = World.of("Twintania", 33, this);
            /**
             * The world Zodiark
             */
            public final World zodiark = World.of("Zodiark", 42, this);

            @Override
            public List<World> worlds() {
                return List.of(alpha, lich, odin, phoenix, raiden, shiva, twintania, zodiark);
            }

            @Override
            public int id() {
                return 7;
            }

            @Override
            public Region region() {
                return europe;
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
         * The aether data center
         *
         * @return aether data center
         */
        public Aether aether() {
            return aether;
        }

        /**
         * The crystal data center
         *
         * @return crystal data center
         */
        public Crystal crystal() {
            return crystal;
        }

        /**
         * The primal data center
         *
         * @return primal data center
         */
        public Primal primal() {
            return primal;
        }

        @Override
        public List<DataCenter> datacenters() {
            return List.of(aether, crystal, primal);
        }

        @Override
        public String name() {
            return "North-America";
        }

        /**
         * The aether data center
         */
        public static final class Aether implements DataCenter {
            /**
             * The world Adamantoise
             */
            public final World adamantoise = World.of("Adamantoise", 73, this);
            /**
             * The world Cactuar
             */
            public final World cactuar = World.of("Cactuar", 79, this);
            /**
             * The world Faerie
             */
            public final World faerie = World.of("Faerie", 54, this);
            /**
             * The world Gilgamesh
             */
            public final World gilgamesh = World.of("Gilgamesh", 63, this);
            /**
             * The world Jenova
             */
            public final World jenova = World.of("Jenova", 40, this);
            /**
             * The world Midgardsormr
             */
            public final World midgardsormr = World.of("Midgardsormr", 65, this);
            /**
             * The world Sargatanas
             */
            public final World sargatanas = World.of("Sargatanas", 99, this);
            /**
             * The world Siren
             */
            public final World siren = World.of("Siren", 57, this);

            @Override
            public List<World> worlds() {
                return List.of(adamantoise, cactuar, faerie, gilgamesh, jenova, midgardsormr, sargatanas, siren);
            }

            @Override
            public int id() {
                return 4;
            }

            @Override
            public Region region() {
                return northAmerica;
            }
        }

        /**
         * The crystal data center
         */
        public static final class Crystal implements DataCenter {
            /**
             * The world Balmung
             */
            public final World balmung = World.of("Balmung", 91, this);
            /**
             * The world Brynhildr
             */
            public final World brynhildr = World.of("Brynhildr", 34, this);
            /**
             * The world Coeurl
             */
            public final World coeurl = World.of("Coeurl", 74, this);
            /**
             * The world Diabolos
             */
            public final World diabolos = World.of("Diabolos", 62, this);
            /**
             * The world Goblin
             */
            public final World goblin = World.of("Goblin", 81, this);
            /**
             * The world Malboro
             */
            public final World malboro = World.of("Malboro", 75, this);
            /**
             * The world Mateus
             */
            public final World mateus = World.of("Mateus", 37, this);
            /**
             * The world Zalera
             */
            public final World zalera = World.of("Zalera", 41, this);

            @Override
            public List<World> worlds() {
                return List.of(balmung, brynhildr, coeurl, diabolos, goblin, malboro, mateus, zalera);
            }

            @Override
            public int id() {
                return 8;
            }

            @Override
            public Region region() {
                return northAmerica;
            }
        }

        /**
         * The primal data center
         */
        public static final class Primal implements DataCenter {
            /**
             * The world Behemoth
             */
            public final World behemoth = World.of("Behemoth", 78, this);
            /**
             * The world Excalibur
             */
            public final World excalibur = World.of("Excalibur", 93, this);
            /**
             * The world Exodus
             */
            public final World exodus = World.of("Exodus", 53, this);
            /**
             * The world Famfrit
             */
            public final World famfrit = World.of("Famfrit", 35, this);
            /**
             * The world Hyperion
             */
            public final World hyperion = World.of("Hyperion", 95, this);
            /**
             * The world Lamia
             */
            public final World lamia = World.of("Lamia", 55, this);
            /**
             * The world Leviathan
             */
            public final World leviathan = World.of("Leviathan", 64, this);
            /**
             * The world Ultros
             */
            public final World ultros = World.of("Ultros", 77, this);

            @Override
            public List<World> worlds() {
                return List.of(behemoth, excalibur, exodus, famfrit, hyperion, lamia, leviathan, ultros);
            }

            @Override
            public int id() {
                return 5;
            }

            @Override
            public Region region() {
                return northAmerica;
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
         * The materia data center
         *
         * @return materia data center
         */
        public Materia materia() {
            return materia;
        }

        @Override
        public List<DataCenter> datacenters() {
            return Collections.singletonList(materia);
        }

        @Override
        public String name() {
            return "Oceania";
        }

        /**
         * The materia data center
         */
        public static final class Materia implements DataCenter {
            /**
             * The world Bismarck
             */
            public final World bismarck = World.of("Bismarck", 22, this);
            /**
             * The world Ravana
             */
            public final World ravana = World.of("Ravana", 21, this);
            /**
             * The world Sephirot
             */
            public final World sephirot = World.of("Sephirot", 86, this);
            /**
             * The world Sophia
             */
            public final World sophia = World.of("Sophia", 87, this);
            /**
             * The world Zurvan
             */
            public final World zurvan = World.of("Zurvan", 88, this);

            @Override
            public List<World> worlds() {
                return List.of(bismarck, ravana, sephirot, sophia, zurvan);
            }

            @Override
            public int id() {
                return 9;
            }

            @Override
            public Region region() {
                return oceania;
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
        public List<DataCenter> datacenters() {
            return List.of(elemental, gaia, mana, meteor);
        }

        @Override
        public String name() {
            return "Japan";
        }


        /**
         * The elemental data center
         *
         * @return elementaldata center
         */
        public Elemental elemental() {
            return elemental;
        }

        /**
         * The gaia data center
         *
         * @return gaia data center
         */
        public Gaia gaia() {
            return gaia;
        }

        /**
         * The mana data center
         *
         * @return mana data center
         */
        public Mana mana() {
            return mana;
        }

        /**
         * The meteor data center
         *
         * @return meteor data center
         */
        public Meteor meteor() {
            return meteor;
        }

        /**
         * The elemental data center
         */
        public static final class Elemental implements DataCenter {
            /**
             * The world Aegis
             */
            public final World aegis = World.of("Aegis", 90, this);
            /**
             * The world Atomos
             */
            public final World atomos = World.of("Atomos", 68, this);
            /**
             * The world Carbuncle
             */
            public final World carbuncle = World.of("Carbuncle", 45, this);
            /**
             * The world Garuda
             */
            public final World garuda = World.of("Garuda", 58, this);
            /**
             * The world Gungnir
             */
            public final World gungnir = World.of("Gungnir", 94, this);
            /**
             * The world Kujata
             */
            public final World kujata = World.of("Kujata", 49, this);
            /**
             * The world Tonberry
             */
            public final World tonberry = World.of("Tonberry", 72, this);
            /**
             * The world Typhon
             */
            public final World typhon = World.of("Typhon", 50, this);

            @Override
            public List<World> worlds() {
                return List.of(aegis, atomos, carbuncle, garuda, gungnir, kujata, tonberry, typhon);
            }

            @Override
            public int id() {
                return 1;
            }

            @Override
            public Region region() {
                return japan;
            }
        }

        /**
         * The gaia data center
         */
        public static final class Gaia implements DataCenter {
            /**
             * The world Alexander
             */
            public final World alexander = World.of("Alexander", 43, this);
            /**
             * The world Bahamut
             */
            public final World bahamut = World.of("Bahamut", 69, this);
            /**
             * The world Durandal
             */
            public final World DURANDAL = World.of("Durandal", 92, this);
            /**
             * The world Fenrir
             */
            public final World fenrir = World.of("Fenrir", 46, this);
            /**
             * The world Ifrit
             */
            public final World ifrit = World.of("Ifrit", 59, this);
            /**
             * The world Ridill
             */
            public final World ridill = World.of("Ridill", 98, this);
            /**
             * The world Tiamat
             */
            public final World tiamat = World.of("Tiamat", 76, this);
            /**
             * The world Ultima
             */
            public final World ultima = World.of("Ultima", 51, this);

            @Override
            public List<World> worlds() {
                return List.of(alexander, bahamut, DURANDAL, fenrir, ifrit, ridill, tiamat, ultima);
            }

            @Override
            public int id() {
                return 2;
            }

            @Override
            public Region region() {
                return japan;
            }
        }

        /**
         * The mana data center
         */
        public static final class Mana implements DataCenter {
            /**
             * The world Anima
             */
            public final World anima = World.of("Anima", 44, this);
            /**
             * The world Asura
             */
            public final World asura = World.of("Asura", 23, this);
            /**
             * The world Chocobo
             */
            public final World chocobo = World.of("Chocobo", 70, this);
            /**
             * The world Hades
             */
            public final World hades = World.of("Hades", 47, this);
            /**
             * The world Ixion
             */
            public final World ixion = World.of("Ixion", 48, this);
            /**
             * The world Masamune
             */
            public final World masamune = World.of("Masamune", 96, this);
            /**
             * The world Pandaemonium
             */
            public final World pandaemonium = World.of("Pandaemonium", 28, this);
            /**
             * The world Titan
             */
            public final World titan = World.of("Titan", 61, this);

            @Override
            public List<World> worlds() {
                return List.of(anima, asura, chocobo, hades, ixion, masamune, pandaemonium, titan);
            }

            @Override
            public int id() {
                return 3;
            }

            @Override
            public Region region() {
                return japan;
            }
        }

        /**
         * The elemental data center
         */
        public static final class Meteor implements DataCenter {
            /**
             * The world Belias
             */
            public final World belias = World.of("Belias", 24, this);
            /**
             * The world Mandragora
             */
            public final World mandragora = World.of("Mandragora", 82, this);
            /**
             * The world Ramuh
             */
            public final World ramuh = World.of("Ramuh", 60, this);
            /**
             * The world Shinryu
             */
            public final World shinryu = World.of("Shinryu", 29, this);
            /**
             * The world Unicorn
             */
            public final World unicorn = World.of("Unicorn", 30, this);
            /**
             * The world Valefor
             */
            public final World valefor = World.of("Valefor", 52, this);
            /**
             * The world Yojimbo
             */
            public final World yojimbo = World.of("Yojimbo", 31, this);
            /**
             * The world Zeromus
             */
            public final World zeromus = World.of("Zeromus", 32, this);

            @Override
            public List<World> worlds() {
                return List.of(belias, mandragora, ramuh, shinryu, unicorn, valefor, yojimbo, zeromus);
            }

            @Override
            public int id() {
                return 10;
            }

            @Override
            public Region region() {
                return japan;
            }
        }
    }

    /**
     * The 中国 region
     */
    public static class China implements Region {
        private final Chocobo chocobo = new Chocobo();
        private final Moguli moguli = new Moguli();
        private final FatCat fatCat = new FatCat();
        private final DoDoChai doDoChai = new DoDoChai();

        @Override
        public List<DataCenter> datacenters() {
            return List.of(chocobo, moguli, fatCat, doDoChai);
        }

        @Override
        public String name() {
            return "中国";
        }

        /**
         * The 陆行鸟 data center
         *
         * @return 陆行鸟 data center
         */
        public Chocobo chocobo() {
            return chocobo;
        }

        /**
         * The 莫古力 data center
         *
         * @return 莫古力 data center
         */
        public Moguli moguli() {
            return moguli;
        }

        /**
         * The 猫小胖 data center
         *
         * @return 猫小胖 data center
         */
        public FatCat fatCat() {
            return fatCat;
        }

        /**
         * The 豆豆柴 data center
         *
         * @return 豆豆柴 data center
         */
        public DoDoChai doDoChai() {
            return doDoChai;
        }

        /**
         * The 陆行鸟 data center
         */
        public static final class Chocobo implements DataCenter {
            /**
             * The world 红玉海
             */
            public final World hongYuHai = World.of("红玉海", 1167, this);
            /**
             * The world 神意之地
             */
            public final World shenYiZhiDi = World.of("神意之地", 1081, this);
            /**
             * The world 拉诺西亚
             */
            public final World laNuoXiYa = World.of("拉诺西亚", 1042, this);
            /**
             * The world 幻影群岛
             */
            public final World huanYingQunDao = World.of("幻影群岛", 1044, this);
            /**
             * The world 萌芽池
             */
            public final World mengYaChi = World.of("萌芽池", 1060, this);
            /**
             * The world 宇宙和音
             */
            public final World yuZhouHeYin = World.of("宇宙和音", 1173, this);
            /**
             * The world 沃仙曦染
             */
            public final World woXianXiRan = World.of("沃仙曦染", 1174, this);
            /**
             * The world 晨曦王座
             */
            public final World chenXiWangZuo = World.of("晨曦王座", 1175, this);

            @Override
            public List<World> worlds() {
                return List.of(hongYuHai, shenYiZhiDi, laNuoXiYa, huanYingQunDao, mengYaChi, yuZhouHeYin, woXianXiRan, chenXiWangZuo);
            }

            @Override
            public int id() {
                return 0;
            }

            @Override
            public Region region() {
                return china;
            }
        }

        /**
         * The 莫古力 data center
         */
        public static final class Moguli implements DataCenter {
            /**
             * The world 白银乡
             */
            public final World baiYinXiang = World.of("白银乡", 1172, this);
            /**
             * The world 白金幻象
             */
            public final World baiJinHuanXiang = World.of("白金幻象", 1076, this);
            /**
             * The world 神拳痕
             */
            public final World shenQuanHen = World.of("神拳痕", 1171, this);
            /**
             * The world 潮风亭
             */
            public final World chaoFengTing = World.of("潮风亭", 1170, this);
            /**
             * The world 旅人栈桥
             */
            public final World lvRenZhanQiao = World.of("旅人栈桥", 1113, this);
            /**
             * The world 拂晓之间
             */
            public final World fuXiaoZhiJian = World.of("拂晓之间", 1121, this);
            /**
             * The world 龙巢神殿
             */
            public final World longchaoshendian = World.of("龙巢神殿", 1166, this);
            /**
             * The world 梦羽宝境
             */
            public final World mengYuBaoJing = World.of("梦羽宝境", 1176, this);

            @Override
            public List<World> worlds() {
                return List.of(baiYinXiang, baiJinHuanXiang, shenQuanHen, chaoFengTing, lvRenZhanQiao, fuXiaoZhiJian, longchaoshendian, mengYuBaoJing);
            }

            @Override
            public int id() {
                return 0;
            }

            @Override
            public Region region() {
                return china;
            }
        }

        /**
         * The 猫小胖 data center
         */
        public static final class FatCat implements DataCenter {
            /**
             * The world 紫水栈桥
             */
            public final World ziShuiZhanQiao = World.of("紫水栈桥", 1043, this);
            /**
             * The world 延夏
             */
            public final World yanXia = World.of("延夏", 1169, this);
            /**
             * The world 静语庄园
             */
            public final World jingYuZhuangYuan = World.of("静语庄园", 1106, this);
            /**
             * The world 摩杜纳
             */
            public final World moDuNa = World.of("摩杜纳", 1045, this);
            /**
             * The world 海猫茶屋
             */
            public final World haiMaoChaWu = World.of("海猫茶屋", 1177, this);
            /**
             * The world 柔风海湾
             */
            public final World rouFengHaiWan = World.of("柔风海湾", 1178, this);
            /**
             * The world 琥珀原
             */
            public final World huPoYuan = World.of("琥珀原", 1179, this);

            @Override
            public List<World> worlds() {
                return List.of(ziShuiZhanQiao, yanXia, jingYuZhuangYuan, moDuNa, haiMaoChaWu, rouFengHaiWan, huPoYuan);
            }

            @Override
            public int id() {
                return 0;
            }

            @Override
            public Region region() {
                return china;
            }
        }

        /**
         * The 豆豆柴 data center
         */
        public static final class DoDoChai implements DataCenter {
            /**
             * The world 水晶塔
             */
            public final World shuiJingTa2 = World.of("水晶塔", 1192, this);
            /**
             * The world 银泪湖
             */
            public final World yinLeiHu = World.of("银泪湖", 1183, this);
            /**
             * The world 太阳海岸
             */
            public final World taiYangHaiAn2 = World.of("太阳海岸", 1180, this);
            /**
             * The world 伊修加德
             */
            public final World yiXiuJiaDe2 = World.of("伊修加德", 1186, this);
            /**
             * The world 红茶川
             */
            public final World hongChaChuan2 = World.of("红茶川", 1201, this);
            /**
             * The world 黄金谷
             */
            public final World huangJinGu = World.of("黄金谷", 1068, this);
            /**
             * The world 月牙湾
             */
            public final World YueYaWan = World.of("月牙湾", 1064, this);
            /**
             * The world 雪松原
             */
            public final World XueSongYuan = World.of("雪松原", 1187, this);

            @Override
            public List<World> worlds() {
                return List.of(shuiJingTa2, yinLeiHu, taiYangHaiAn2, yiXiuJiaDe2, hongChaChuan2, huangJinGu, YueYaWan, XueSongYuan);
            }

            @Override
            public int id() {
                return 0;
            }

            @Override
            public Region region() {
                return china;
            }
        }
    }
}
